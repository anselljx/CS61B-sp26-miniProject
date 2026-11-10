# proj02 日志
首先我花了很长时间理解ArrayList本质
## 第一个困难发生在tolist
arraylist现在内部构造器设置好nextfirst和nextlast，数组循环，插入起始点由前两个参数决定。而tolist则是将循环数组转化为单项数组。因此items中的位置没有什么意义，只是地址。地址转化为tolist的逻辑下标是难点。
## 解决问题后后面几个函数难度不大
## 最后resize函数有难度
resize就是抛弃原始的items了，新建items则完全按照逻辑下标的顺序。本质还是循环数组，nextfirst和nextlast也分布在新items尾部和内存尾部。
