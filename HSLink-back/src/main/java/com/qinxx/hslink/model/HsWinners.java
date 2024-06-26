package com.qinxx.hslink.model;

import com.qinxx.hslink.model.base.BaseEntity;

/**
 * @author by PHY
 * @Classname HsWinners
 * @date 2021-06-02 11:31
 */
public class HsWinners extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 人员id
     */
    private String userId;

    /** 人员姓名 */
    private String userName;

    /** 获奖类型 */
    private String type;

    /** 获奖人职务 */
    private String post;

    /** 版本号 */
    private String version;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setPost(String post)
    {
        this.post = post;
    }

    public String getPost()
    {
        return post;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }

    @Override
    public String toString() {
        return "HsWinners{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", post='" + post + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
