# CommonDialog
#####################################################################################################################
#@author luzhenyu
#@version 1.0
#@date 2015/05/01
#@describe 该库包含功能有：自定义Dialog并可以定义其位置，
在Dialog基础上可以自定义其中的内容，
这里实现了常规的仿IOS形式的Dialog，
省市地三级联动，
日期选择，
仿IOS 从底部弹出选择条目  包括：带有标题和无标题两种形式
#@describe


# TO USE

1.常规Dialog
通过new MyStandaradDialog(yourActivity).builder(gravity)创建一个新的Dialog
int gravity = 0(默认居中) 1(底部) 其他(顶部)
可以设定的方法有setTitle、setMsg、setEditText、setCancelable、setPositiveButton、setNegativeButton调用哪个方法就会在布局中自动添加哪个控件View
也可以通过setView自定义view去构造Dialog中间部分的布局、最后通过show方法显示、getResult方法是获取setEditText方法文本中的字符串。

2.底部弹出条目
通过new MySheetDialog(yourActivity).builder()创建一个新的弹出条目，“取消”条目是必须有的
setCancelable(true)设定可取消
setCanceledOnTouchOutside(true)设定点击Dialog以外的部分可消失
addSheetItem("条目上文字", SheetItemColor.Blue（颜色）, new OnSheetItemClickListener()（点击回调）)添加条目
如果调用了setTitle("提示")方法，那么就是含有标题的条目
最后通过show方法使这个窗口弹出来。

3.三级联动
就是在Dialog基础上在内部添加了一个自定义的view布局
通过View view = new GetCityWheel(yourActivity.this).getCityView();获取view实例
调用功能1中的setView(view)方法将view添加到Dialog中去，
通过GetCityWheel.getTimeResultStr()方法获取选择结果

4.日期选择
用法和功能3一样
通过View view = new GetDateWheel(yourActivity.this,currentTime（传null表示选取当前时间为默认显示时间）).getTimeView();获取view实例
通过GetDateWheel.getTimeResult()方法获取选择结果

5.彩虹特效的圆形进度对话框
通过MyCriProgressDialog.creatDialog(youActivity.this)创建实例 然后就可以按照一般的Dialog使用了

6.二级联动（时间选择器）
同上，调用的是wheelMain = new WheelMain(timepickerview, context, true, true);构造

7.五级联动（日期时间选择器）
同上，调用的是WheelMain(View view, Context context, boolean hasSelectTime)的构造



#################@important     资源文件请全部保持一致，也可通过源码自己修改     important@#################


#####################################################################################################################
						不断完善中......
#####################################################################################################################