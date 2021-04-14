package moabi.lsp.lsplsp.service.serviceImpl;

import moabi.lsp.lsplsp.entity.user;
import moabi.lsp.lsplsp.mapper.userMapper;
import moabi.lsp.lsplsp.service.UserService;
import moabi.lsp.lsplsp.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    //@Resource
    @Autowired
    private userMapper userMapper;
    //注册
    @Override
    public void regist(user use) {
        //获取盐值
        String salt = SaltUtils.getSalt(10);
        //保存盐值
        use.setSalt(salt);
        System.out.println("盐值："+salt);
        //对用户密码进行加密
        Md5Hash md5Hash = new Md5Hash(use.getPwd(), salt, 128);
        System.out.println("加密用户密码："+md5Hash.toHex());
        use.setPwd(md5Hash.toHex());
        System.out.println("保存加密密码成功：");
        userMapper.adduser(use);
    }

    //登录
    @Override
    public user findByName(String name) {


        return userMapper.finByname(name);
    }
}
