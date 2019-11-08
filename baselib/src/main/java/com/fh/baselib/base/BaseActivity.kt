package com.fh.baselib.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.fh.baselib.utils.ActivityManagers
import com.fh.baselib.utils.ToastUtil
import com.fh.baselib.widget.CustomToolBar
import com.fh.baselib.widget.LoadingDialog
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.umeng.analytics.MobclickAgent
import gorden.rxbus2.RxBus


/**
 * Author: Austin
 * Date: 19-3-28
 * Description: Activity基类
 */
abstract class BaseActivity : RxAppCompatActivity() , CustomToolBar.OnClickLeftListener, CustomToolBar.OnClickRightListener,CustomToolBar.OnClickTvRightListener,View.OnClickListener {
    private val BASE_VIEW_ID: Int = com.fh.baselib.R.layout.activity_base
    lateinit var mContext: Context
    lateinit var customToolBar: CustomToolBar
    lateinit var loadingDialog: LoadingDialog

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        if (isFullScreen()) {
//            ActivityUtil.transparentStatusBar(window)
            goneSystemUi()
        } else {
            setStatusBar(!isFullScreen(),!isFullScreen())
            setStatusTextColor()
        }
        /*if (isFullScreen()) {
//            ActivityUtil.transparentStatusBar(window)
            ActivityUtil.setStatusBarTransparent(window)
        } else {
//            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.bg_toolbar))
//            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.transparent))
//            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.white))

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            }

            //修改状态栏字体颜色
//            setStatusTextColor()
//            setStatusColor()

        }*/
//        UtilsStyle.statusBarLightMode(this)



        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//禁止横屏

        super.onCreate(savedInstanceState)
        setContentView(initRootView())

        ActivityManagers.instance.addActivity(this)
        RxBus.get().register(this)
        loadingDialog = LoadingDialog(mContext)
        initView()
        initListener()
        initData()

    }

    /**
     * 初始化根部View 并添加自定义的ToolBar
     */
    private fun initRootView(): View {
        var baseView: LinearLayout = layoutInflater.inflate(BASE_VIEW_ID,null) as LinearLayout
        customToolBar = CustomToolBar(this)
        customToolBar.onClickLeftListener = this
        customToolBar.onClickTvRightListener = this
        customToolBar.onClickRightListener = this
        baseView.addView(customToolBar)


        if (layoutId() != 0) {
            var subView: View = getLayoutInflater().inflate(layoutId(), null)
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
            subView.setLayoutParams(params)
            baseView.addView(subView)
        }



        return baseView
    }

    /**
     * 加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 设置事件
     */
    abstract fun initListener()

    override fun onClick(v: View?) {

    }

    /**
     * 设置标题
     */
    fun setToolbarTitle(title: String) {

        customToolBar!!.setTitle(title)
    }

    /**
     * 设置标题
     */
    fun setToolbarTitle(title: Int) {
        customToolBar!!.setTitle(title)
    }

    /**
     * 是否显示标题栏
     */
    fun showToolbar(visibility: Int) {
        customToolBar!!.visibility = visibility
    }

    /**
     * 是否显示标题栏
     */
    fun showToolbar(bool: Boolean) {
        customToolBar!!.visibility = if (bool) View.VISIBLE else View.GONE
    }

    /**
     * Toolbar 左边ICON点击事件
     */
    override fun onClickLeft(view: View) {
        finish();
    }

    /**
     * Toolbar 右边ICON点击事件
     */
    override fun onClickRight(view: View) {
    }

    override fun onClickTvRight(view: View) {

    }

    fun showRightIcon(visibility: Int) {
        customToolBar?.showRightIcon(visibility)
    }

    override fun onStart() {
        super.onStart()
//        L.d("onStart")
    }

    override fun onResume() {
        super.onResume()
//        L.d("onResume")
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
//        L.d("onPause")
        MobclickAgent.onPause(this)
    }

    override fun onStop() {
        super.onStop()
//        L.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
//        L.d("")
        if (loadingDialog?.isShowing!!)
            loadingDialog?.dismiss()
        RxBus.get().unRegister(this)
        ActivityManagers.instance.removeActivity(this)

    }

    /**
     * 显示进度条
     */
    open fun showDialog() {
        if (loadingDialog != null && !loadingDialog?.isShowing!!)
            loadingDialog?.show()
    }

    /**
     * 取消进度条显示
     */
    open fun dismissDialog() {
        if (loadingDialog?.isShowing!!)
            loadingDialog?.dismiss()
    }
    /**
     * 显示短时间吐司
     */
    fun toast(msg : String) {
        ToastUtil.show(mContext,msg)
    }

    /**
     * 显示一个长时间的吐司
     */
    fun toastLong(msg : String) {
        ToastUtil.showLong(mContext,msg)
    }

    /**
     * 是否显示网络状态： true ，表示一直显示，不管是否链接都显示
     */
//    fun showNetStatus():Boolean {
//        return false
//    }

    open fun isFullScreen(): Boolean {
        return false
    }

    fun addFragment(viewId: Int,fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(viewId,fragment)
//        fragmentTransaction.commit()
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun setStatusTextColor() {
        //https://blog.csdn.net/dummyo/article/details/90108788
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏字体颜色为黑色
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusColor() {
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏背景色
        window.setStatusBarColor(resources.getColor(com.fh.baselib.R.color.bg_toolbar))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusColor(@ColorRes id: Int) {
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏背景色
        window.setStatusBarColor(resources.getColor(id))
    }


    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
//    protected boolean useThemestatusBarColor = false;
//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
//    protected boolean useStatusBarColor = true;

    public fun setStatusBar(useThemestatusBarColor: Boolean,useStatusBarColor: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                window.statusBarColor = resources.getColor(com.fh.baselib.R.color.bg_toolbar)//设置状态栏背景色
            } else {
                window.statusBarColor = Color.TRANSPARENT//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags =
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        } else {
//            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show()
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
//            window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//
//        }
    }

//    @Subscribe(code = 70010, threadMode = ThreadMode.MAIN)
//    open fun receive70010() {
//        LogUtil.e("Token失效请先登录.")
//        ARouter.getInstance().build("/login/login")
//            .navigation()
//    }

    /**
     * 隐藏虚拟按键
     */
    fun goneSystemUi() {
        //隐藏虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            val v = window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }

    /**
     * 显示虚拟按键
     */
    fun visibleSystemUi() {
        //显示虚拟键盘
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            //低版本sdk
            val v = window.decorView
            v.systemUiVisibility = View.VISIBLE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions
        }
    }
}