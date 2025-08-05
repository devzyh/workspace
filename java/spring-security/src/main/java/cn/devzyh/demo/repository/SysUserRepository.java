package cn.devzyh.demo.repository;

import cn.devzyh.demo.domain.SysUser;
import org.springframework.data.repository.CrudRepository;

public interface SysUserRepository extends CrudRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
