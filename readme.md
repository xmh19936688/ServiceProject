#service各种玩法

##NotificationServiceDemo
带通知的service
- ongoing通知
- 前台service（在app退出时不被结束）
- 在service中持续更新通知内容

##StartServiceMoreTimes
多次start同一个service

##知识点

###取消ongoing的通知
NotificationManager.cancel(notificationID);

###设置前台service
在`onStartCommand`中调用`startForeground(NOTIFICATION_ID, builder.build());`

###多次通过`Context.startService()`启动同一个service
- onCreate只执行一次
- onStartCommand每次都执行

###线程
- 当线程执行完run方法后进入死亡状态，不能再被start，如果需要再次启动则需重新new该线程