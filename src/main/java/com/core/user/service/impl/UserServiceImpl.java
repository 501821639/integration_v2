package com.core.user.service.impl;

import com.core.common.bean.Region;
import com.core.common.dao.PagingDao;
import com.core.common.dao.RegionDao;
import com.core.company.bean.Company;
import com.core.company.bean.CompanyNature;
import com.core.company.bean.CompanyType;
import com.core.company.dao.CompanyDao;
import com.core.company.dao.CompanyNatureDao;
import com.core.company.dao.CompanyTypeDao;
import com.core.company.dto.CompanyBriefnessMessage;
import com.core.user.bean.*;
import com.core.user.dao.*;
import com.core.user.dto.UserAllMessage;
import com.core.user.dto.UserAuthResult;
import com.core.user.service.UserService;
import com.shiro.realm.CustomRealm;
import com.utils.date.FormatType;
import com.utils.json.FormatJson;
import com.utils.RandomGenerate;
import com.utils.regular.UserReg;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PagingDao pagingDao;

    @Resource
    private CustomRealm customRealm;

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private CompanyDao companyDao;

    @Resource
    private UserCompanyDao userCompanyDao;

    @Resource
    private CompanyTypeDao companyTypeDao;

    @Resource
    private CompanyNatureDao companyNatureDao;

    @Resource
    private RegionDao regionDao;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public UserAuthResult createUserAuth(String key, String val) {

        UserAuthResult userAuthResult = new UserAuthResult();

        //账号是否合法
        if (key == null || !new UserReg().phone(key)) {
            userAuthResult.setCode("error");
            userAuthResult.setResult("账号为手机号码,请输入正确的手机号码");
            return userAuthResult;
        }

        // 用户是否注册
        List<?> list = userDao.queryUser("from User u where u.username='" + key + "'");
        if (!list.isEmpty()) {
            userAuthResult.setCode("error");
            userAuthResult.setResult(key + " 账号已被注册");
            return userAuthResult;
        }

        Date d = new Date();

        //上次验证码生成未失效
        if (val != null) {

            //上次验证时间
            long time = Long.parseLong(val.split(",")[1]) + 60000;

            if (d.getTime() < time) {
                userAuthResult.setCode("error");
                userAuthResult.setResult("用户注册：验证码请求间隔限制1分钟");
                return userAuthResult;
            }

        }

        userAuthResult.setCode("success");
        userAuthResult.setAuth(new RandomGenerate().generateN(6));
        userAuthResult.setDate(d);
        userAuthResult.setResult("验证码已发送至" + key + ",10分钟有效");

        System.out.println(key + "：" + userAuthResult.getAuth());

        return userAuthResult;
    }

    @Override
    public String requiredRegisterUser(User user, String userAuth, String sessionAuth) {

        UserReg ur = new UserReg();

        if (user.getUsername() == null || !ur.phone(user.getUsername())) {
            return "账号为手机号码,请输入正确的手机号码";
        }

        List<?> list = userDao.queryUser("from User u where u.username='" + user.getUsername() + "'");
        if (list.size() > 0) {
            return "账号已被注册";
        }

        if (sessionAuth == null) {
            return "请先获取验证码";
        }

        if (userAuth == null || userAuth.trim().equals("")) {
            return "请输入验证码";
        }

        if (!userAuth.equals(sessionAuth.split(",")[0])) {
            return "验证码错误";
        }

        if (user.getPassword() == null || !ur.password(user.getPassword())) {
            return "密码由8-16位、字母、数字组成、不能有空格";
        }

        //设置密码加密
        user.setPassword(new Md5Hash(user.getPassword(), user.getUsername(), 2017).toString());

        //初始化数据防止恶意添加数据
        user.setPortrait("/adminAsx/images/default-avatar.jpg");//默认头像
        user.setLocked(1);//不锁定账号
        user.setMail(null);//邮箱
        user.setCard(null);//姓名
        user.setIdCard(null);//身份证号码
        user.setRegTime(new Date());//注册时间

        userDao.addUser(user);

        return "success";
    }

    @Override
    public UserAllMessage requiredLogin(String username) {

        logger.info(username + "账号尝试登录系统");

        username = username.replace(" ", "");

        if (!new UserReg().phone(username)) {
            return null;
        }

        String hql = "from User u where u.username='" + username + "'";
        List<?> list = userDao.queryUser(hql);

        User user;
        UserAllMessage userAllMessage;

        if (list.isEmpty() || list.size() > 1) {
            return null;
        } else {

            user = (User) list.get(0);

            userAllMessage = new UserAllMessage();
            userAllMessage.setId(user.getId());
            userAllMessage.setPortrait(user.getPortrait());
            userAllMessage.setUsername(user.getUsername());
            userAllMessage.setPassword(user.getPassword());
            userAllMessage.setLocked(user.getLocked());
            userAllMessage.setMail(user.getMail());
            userAllMessage.setCard(user.getCard());
            userAllMessage.setIdCard(user.getIdCard());
            userAllMessage.setRegTime(user.getRegTime());

            hql = "from UserRole ur where ur.userId='" + user.getId() + "'";

            List<?> userRoleList = userRoleDao.queryUserRole(hql);

            //获取用户所有角色
            List<Role> roleList = new ArrayList<>();

            for (Object o : userRoleList) {
                UserRole userRole = (UserRole) o;
                Role role = roleDao.getRole(userRole.getRoleId());
                if (role == null) {
                    userRoleDao.deleteUserRole(userRole);
                } else {
                    roleList.add(role);
                }
            }

            userAllMessage.setRoleList(roleList);

            // 获取用户所有角色下对应的菜单权限
            List<RolePermission> rolePermissionList = new ArrayList<>();

            for (Role role : roleList) {
                hql = "from RolePermission rp where rp.roleId='" + role.getId() + "'";
                List<?> rolePermissionListFor = rolePermissionDao.queryRolePermission(hql);
                for (Object o : rolePermissionListFor) {
                    RolePermission rolePermission = (RolePermission) o;
                    rolePermissionList.add(rolePermission);
                }
            }

            List<Permission> permissionList = new ArrayList<>();

            for (RolePermission rolePermission : rolePermissionList) {
                Permission permission = permissionDao.getPermission(rolePermission.getPermissionId());
                if (permission == null) {
                    rolePermissionDao.deleteRolePermission(rolePermission);
                    continue;
                }
                if (!permission.getType().equals("menu")) {
                    continue;
                }
                boolean b = false;//默认不重复
                for (Permission fPermission : permissionList) {//不添加角色重复菜单
                    if (permission.getId().equals(fPermission.getId())) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    permissionList.add(permission);
                }
            }

            //权限排序
            permissionList.sort(new Comparator<Permission>() {
                @Override
                public int compare(Permission p1, Permission p2) {
                    return p1.getOrder() - p2.getOrder();
                }
            });

            userAllMessage.setPermissionList(permissionList);

            //获取用户所属单位
            List<?> userCompanies = userCompanyDao.queryUserCompany("from UserCompany uc where uc.userId='" + userAllMessage.getId() + "'");
            List<CompanyBriefnessMessage> companyBriefnessMessages = new ArrayList<>();

            for (Object o : userCompanies) {

                UserCompany userCompany = (UserCompany) o;
                Company company = companyDao.getCompany(userCompany.getCompanyId());

                if (company == null) {
                    userCompanyDao.deleteUserCompany(userCompany);
                } else {

                    CompanyBriefnessMessage companyBriefnessMessage = new CompanyBriefnessMessage();

                    //单位id
                    companyBriefnessMessage.setId(company.getId());

                    //单位名称
                    companyBriefnessMessage.setName(company.getName());

                    //上级单位链
                    String companyId = company.getpId();
                    StringBuilder superName = new StringBuilder();
                    if (!companyId.equals("0")) {
                        while (true) {
                            Company superCompany = companyDao.getCompany(companyId);
                            if (superCompany == null) {
                                break;
                            } else {
                                superName.append(superCompany.getName());
                                if (!superCompany.getpId().equals("0")) {
                                    superName.append("&nbsp;->&nbsp;");
                                }
                                companyId = superCompany.getpId();
                            }
                        }
                    }
                    companyBriefnessMessage.setSuperName(superName.toString());


                    //单位类型
                    CompanyType companyType = companyTypeDao.getCompanyType(company.getTypeId());
                    if (companyType != null) {
                        companyBriefnessMessage.setTypeName(companyType.getName());
                    }

                    //单位性质
                    CompanyNature companyNature = companyNatureDao.getCompanyNature(company.getNatureId());
                    if (companyNature != null) {
                        companyBriefnessMessage.setNatureName(companyNature.getName());
                    }

                    //单位地区
                    String regionId = company.getRegionId();
                    StringBuilder regionName = new StringBuilder();
                    while (true) {
                        Region superRegion = regionDao.getRegion(regionId);
                        if (superRegion == null) {
                            break;
                        } else {
                            regionName.insert(0, superRegion.getName());
                            regionId = superRegion.getpId();
                        }
                    }
                    companyBriefnessMessage.setRegionName(regionName.toString());

                    //审核状态
                    companyBriefnessMessage.setCheck(company.getCheck());

                    //注册时间
                    companyBriefnessMessage.setRegTime(company.getRegTime());

                    companyBriefnessMessages.add(companyBriefnessMessage);

                }
            }

            userAllMessage.setCompanyBriefnessMessageList(companyBriefnessMessages);

        }

        return userAllMessage;

    }

    @Override
    public String pageUser(int page, int length, User user) {

        String hql = "from User u where 1=1 order by u.id desc";

        List<?> list = (List<?>) pagingDao.pageQueryObject(hql, page, length);

        return new FormatJson(new FormatType().getYmdhms()).listConversion(list);
    }

    @Override
    public int numberUser(int length, User user) {

        String hql = "select count(*) from User u where 1=1";

        int sum = pagingDao.PageNumberObject(hql), page;

        if (sum % length == 0) {
            page = sum / length;
        } else {
            page = sum / length + 1;
        }

        return page;

    }

    @Override
    public String requiredUpdateUserPortrait(String userId, String portrait) {

        User user = userDao.getUser(userId);

        if (user == null) {
            return "user ID error";
        }

        if (portrait == null || portrait.equals("")) {
            return "parameter error";
        }

        user.setPortrait(portrait);

        userDao.updateUser(user);

        return "success";
    }

    @Override
    public String requiredUpdateUserICM(String userId, String card, String idCard, String mail) {

        User user = userDao.getUser(userId);

        if (user == null) {
            return "user ID error";
        }

        if (user.getCard() == null || user.getCard().trim().equals("")) {

            if (card != null) {

                card = card.trim();

                if (!card.equals("")) {

                    if (card.length() > 20) {
                        return "姓名长度限制20汉字、字符";
                    }

                    user.setCard(card);
                }

            }


        }

        UserReg ur = new UserReg();

        if (user.getId() == null || user.getIdCard().trim().equals("")) {

            if (idCard != null) {

                idCard = idCard.replace(" ", "");

                if (!idCard.equals("")) {

                    if (!ur.idCard(idCard)) {
                        return "身份证号格式不符合要求（二代身份证18位）";
                    }

                    user.setIdCard(idCard);

                }

            }

        }

        if (user.getMail() == null || user.getMail().trim().equals("")) {

            if (mail != null) {

                mail = mail.replace(" ", "");

                if (!mail.equals("")) {

                    if (!ur.mail(mail)) {
                        return "邮箱格式不符合要求";
                    }

                    user.setMail(mail);

                }

            }

        }

        userDao.updateUser(user);

        return "success";
    }


    @Override
    public String clearCached() {
        customRealm.clearCached();
        return "success";
    }

    @Override
    public List<String> queryUserRolePermission(String userId) {

        Set<String> set = new HashSet<>();

        String hql = "from UserRole ur where ur.userId='" + userId + "'";

        List<?> userRoles = userRoleDao.queryUserRole(hql);

        for (Object our : userRoles) {

            UserRole userRole = (UserRole) our;

            hql = "from RolePermission rp where rp.roleId='" + userRole.getRoleId() + "'";

            List<?> rolePermissions = rolePermissionDao.queryRolePermission(hql);

            for (Object orp : rolePermissions) {

                RolePermission rolePermission = (RolePermission) orp;

                Permission permission = permissionDao.getPermission(rolePermission.getPermissionId());

                if (permission.getType().equals("permission")) {
                    set.add(permission.getPermissionCode());
                }

            }

        }

        return new ArrayList<>(set);
    }

    @Override
    public String requiredUpdateUserlocked(String userId, String lockedRemark) {

        User user = userDao.getUser(userId);

        if (user == null) {
            return "用户ID不存在";
        }

        if (user.getLocked() == 0) {
            return "该账号已被锁定";
        }

        if (lockedRemark == null || lockedRemark.trim().equals("")) {
            return "请说明锁定原因";
        }

        user.setLocked(0);
        user.setLockedRemark(lockedRemark.replace(" ", ""));

        if (user.getLockedRemark().length() > 50) {
            return "长度显示50汉字、字符";
        }

        userDao.updateUser(user);

        return "success";
    }

    @Override
    public String requiredUpdateUserlocked(String userId) {

        User user = userDao.getUser(userId);

        if (user == null) {
            return "用户ID不存在";
        }

        if (user.getLocked() == 1) {
            return "该账未被锁定";
        }

        user.setLocked(1);
        user.setLockedRemark(null);

        userDao.updateUser(user);

        return "success";
    }


}
