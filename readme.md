#service各种玩法

##NotificationServiceDemo
带通知的service
- ongoing通知
- 前台service（在app退出时不被结束）
- 在service中持续更新通知内容

##知识点

###取消ongoing的通知
NotificationManager.cancel(notificationID);

###设置前台service
在`onStartCommand`中调用`startForeground(NOTIFICATION_ID, builder.build());`
