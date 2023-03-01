# Somta
## 项目介绍


触发流程的地址
http://localhost:8080/triggerFlow

{
"flowKey": "flow_123",
"flowData": {
    "id":1,
    "name":"明天的地平线"
}
}


1.如何判断每个节点是否执行完成
每一个节点应该有执行状态，只有执行状态处于完成状态的时候才能获取到下一个执行器

2.如果接口的方法之间返回一个基础类型，如String，那接口请求成功后，返回的是一个字符，就不知道怎么取那个值塞给变量了,看看bz的设计


问题：
1.节点的多个子类有自己不同的属性，反序列话的时候如果用父类接收，子类的属性就丢失了
解决办法：https://cloud.tencent.com/developer/article/1889768
https://blog.csdn.net/inrgihc/article/details/118916599