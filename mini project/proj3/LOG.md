# proj3渗透实验，完成于2026.7.27
用时约三小时
## 主要完成内容
percolation内容填写：首先构造一个n*n的布尔数组，判断开关。建立两个uf，判断full和percolation。open将当前位置打开，并将前缀和top联系。注意，full和percolation应该使用两个uf体系，因为底部不连通而上部连通。
