package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.vfunding.common.model.BaseModel;

/**
 * 文件上传类
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class Upfiles extends BaseModel  {
   /**
    * 主键
    */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 模块
     */
    private String code;
    /**
     * 所属模块ID
     */
    private String aid;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 文件类型
     */
    private String filetype;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件大小
     */
    private Integer filesize;

    /**
     * 文件路径
     */
    private String fileurl;

    /**
     * 是否封面
     */
    private Integer ifCover;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 点击次数
     */
    private Integer hits;

    /**
     * 添加时间
     */
    private String addtime;

    /**
     * 添加IP
     */
    private String addip;

    /**
     * 更新时间
     */
    private String updatetime;

    /**
     * 更新IP
     */
    private String updateip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public Integer getIfCover() {
        return ifCover;
    }

    public void setIfCover(Integer ifCover) {
        this.ifCover = ifCover;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getUpdateip() {
        return updateip;
    }

    public void setUpdateip(String updateip) {
        this.updateip = updateip == null ? null : updateip.trim();
    }
}