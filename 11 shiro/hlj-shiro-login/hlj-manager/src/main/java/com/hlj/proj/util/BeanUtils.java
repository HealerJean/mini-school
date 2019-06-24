package com.hlj.proj.util;
import com.hlj.proj.dto.user.UserBasicInfoDTO;
import com.hlj.proj.dto.user.UserInfoDTO;
import com.hlj.proj.dto.user.UserDTO;

/**
 * @ClassName BeanUtils
 * @Author TD
 * @Date 2019/6/14 14:46
 * @Description
 */
public class BeanUtils {


    public static UserDTO toUserDTO(UserInfoDTO userInfo) {
        if(userInfo == null){
            return null;
        }
        UserBasicInfoDTO userBasicInfo = userInfo.getUserBasicInfo();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(userBasicInfo.getUserType());
        userDTO.setUsername(userBasicInfo.getUsername());
        userDTO.setRealName(userBasicInfo.getRealName());
        userDTO.setPassword(userBasicInfo.getPassword());
        userDTO.setTelephone(userBasicInfo.getTelephone());
        userDTO.setEmail(userBasicInfo.getEmail());
        return userDTO;
    }
}
