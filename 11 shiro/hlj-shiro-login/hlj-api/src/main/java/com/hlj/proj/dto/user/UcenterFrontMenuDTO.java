package com.hlj.proj.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UcenterFrontMenuDTO
 * @Author TD
 * @Date 2019/3/21 11:41
 * @Description 前端菜单
 */
@Data
public class UcenterFrontMenuDTO  implements Serializable {

    private String name;
    private String path;
    private UcenterFrontMenuMetaDTO meta;
    private Integer sort;
    private List<UcenterFrontMenuDTO> children;
}
