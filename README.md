# AppPlugin
app插件化demo

- 参考CC配置，https://luckybilly.github.io/CC-website/#/about

- common  模拟公共库

- longingmodel  模拟登录界面他是一个单独的抽离组件

-  LogInterceptor  放在了common公共库中，全局响应拦截

- CC.enableRemoteCC(true); // 默认是false: 关闭状态  要开启跨进程组件调用，来获取用户登录信息，正式发版关闭

-  组件APP登录状态维护流程，在基类里面注册登录状态的Observer，来维护获取当前用户的登录状态，组件当以一个单独APP运行时候开启跨进程，登录模块提供一个获取用户信息的接口，然后其他需要获取信息的去获取，这里的用户信息可以存放在本地或者内存，根据自身的情况定，
组件开发的时候要保持组件之间一直后台运行

- 进行组件之间共享传递的要实现序列化或者以json格式传递

