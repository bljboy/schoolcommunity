# 通知

> ![image-20221129130857166](C:\Users\baoli\AppData\Roaming\Typora\typora-user-images\image-20221129130857166.png)
>
> ![image-20221129131200151](C:\Users\baoli\AppData\Roaming\Typora\typora-user-images\image-20221129131200151.png)
>
> ![image-20221129132250444](C:\Users\baoli\AppData\Roaming\Typora\typora-user-images\image-20221129132250444.png)

# 抽屉绑定控件

```java
NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
navigationView.setNavigationItemSelectedListener(this);
((TextView) headerView.findViewById(R.id.textView_fullName)).setText(PrefUtils.getUsername());
headerView.findViewById(R.id.textView_emailUsername)).setText(PrefUtils.getUserEmail());
```

# 按钮打开抽屉

```java
    private ImageButton appbar_navigation;
    private DrawerLayout drawer_layout;       
        drawer_layout = getActivity().findViewById(R.id.drawer_layout);
        appbar_navigation = getActivity().findViewById(R.id.appbar_navigation);
        appbar_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("appbar", "appbar_navigation ");
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
```

# Tablayout属性

> TabLayout最早是通过反射来改变下标线的长度，现在最新的已经加了属性app:tabIndicatorFullWidth来控制，false时和标题文字的长度相同，true时下标线和tab的长度相同

# appbar隐藏

```
app:layout_scrollFlags="scroll|enterAlways"
```

![image-20221203020328680](C:\Users\baoli\AppData\Roaming\Typora\typora-user-images\image-20221203020328680.png)

