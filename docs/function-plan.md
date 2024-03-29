---
date: 2022-09-26
article: true
order: 2
headerDepth: 1
icon: software
---

# Function Planning

## APP 是什么

一个图片社交APP，服务对象是个人。

## 功能有什么

### APP 必有

- 用户管理系统（基于SSO）
  - SSO 一键登入
  - 用户资料卡
  - 用户相册
    - 列表视图
    - 地点标记地图的视图
- 支持用户分享图片动态
  - 支持点赞
  - 支持发布图片
    - 一个动态可以包含1-6张图片
  - 支持显示位置
  - 支持删除动态
- 支持用户管理自己的相册
  - 用户删除动态的同时，将删除该动态下的所有照片
- 在地图上随机显示周围的人发布的图片（参考设备位置）

### APP 运作必有

- 本地 sqlite
  - 存储本地凭据
  - 已缓存的动态
- 与Android 请求权限打交道
  - 写入设备缓存（非图片格式的图片）
  - 写入设备文件
  - 写入相册图片
- 移动管理
  - 断开网络后的提醒
- 安全机制
  - 支持用户删除所有信息（服务器上和设备内）
  - 仅允许一个设备子在线
- 读写 EXIF

### APP 待定

- 评论动态
  - 新增评论
  - 评论者或者被评论者删除评论
- 受理对违规照片的举报（需要后台驱动或者 webview）

## 参考 Photoview

Github: [photoview](https://github.com/photoview/photoview)

Photoview 是一个简单且用户友好的照片库，专为摄影师而设计，旨在提供一种简单快捷的方式来浏览目录，其中包含数千张高分辨率照片。

您将 Photoview 配置为在文件系统的目录中查找照片和视频。扫描仪会自动拾取您的媒体并开始生成缩略图，以使浏览速度超级快。

扫描您的媒体后，它们会显示在网站上，其组织方式与文件系统上的相同。

![1664431289361.png](https://static-file.hjm.red/2022/09/29/aac14a092974f.png)

可以配置多个用户，每个用户都有自己的照片和视频目录。每个用户都配置了用户名和密码，以保持其媒体的私密性。

通过生成公共或受密码保护的链接，可以轻松共享相册和个人照片或视频。

![1664431348230.png](https://static-file.hjm.red/2022/09/29/f30e9b65c5f9d.png)

当分析照片的 EXIF 元数据时，此信息会显示在照片旁边。如果照片是用手机拍摄的，它很可能包括照片拍摄地点的坐标。这用于在地图上自动显示照片。

当在同一位置拍摄多张照片时，它们会被分组以便一起查看。

![1664431467203.png](https://static-file.hjm.red/2022/09/29/f9d88bc432eeb.png)

使用官方 iOS 应用程序从您的手机快速访问您的整个媒体库。直接在应用程序内导航、分享和下载照片和视频。

![1664431605376.png](https://static-file.hjm.red/2022/09/29/b1899c6f1a808.png)

### 体验

Demo: Visit https://photos.qpqp.dk/ 

- Username: **demo** 
- Password: **demo**

