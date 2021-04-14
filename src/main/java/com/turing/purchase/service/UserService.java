package moabi.lsp.lsplsp.service;

import moabi.lsp.lsplsp.entity.user;

public interface UserService {
    //注册
    void regist(user user);

    //查询
    user findByName(String name);
}
