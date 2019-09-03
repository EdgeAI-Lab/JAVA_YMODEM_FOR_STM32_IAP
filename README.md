# Ymodem
通过Ymodem协议与STM32通信，实现固件升级。

## Ymodem协议简介
![ymodem](https://github.com/FanHongchuang/FOTA/raw/master/doc/ymodem.png)

* ymodem协议详情请参考: [Ymodem](http://pauillac.inria.fr/~doligez/zmodem/ymodem.txt)
* 发送文件名时，应同时发送文件的大小，上图没有标出，但是程序中是有实现的，格式为 ``` SOH  00 FF foo.c 3232 NUL[118] CRCH CRCL ```


## 相关资源
* [STM32 IAP Demo](https://github.com/FanHongchuang/STM32_IAP_Demo)
* [Java Serial](https://github.com/Fazecast/jSerialComm)
* [Ymode](https://github.com/aesirot/ymodem)

## 注意事项
* 使用前请修改串口端口号
* 串口波特率为115200
* 将您的bin文件命名为app.bin，并将其放在工程的resources文件夹下

## 使用方法
* 首先编译[IAP_Binary_Template](https://github.com/FanHongchuang/STM32_IAP_Demo/tree/master/IAP_Binary_Template)项目得到bin文件

* 然后编译并烧写[IAP_Main](https://github.com/FanHongchuang/STM32_IAP_Demo/tree/master/IAP_Main)

* 最后编译启动本项目，根据终端提示的菜单进行操作即可 
