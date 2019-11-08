package com.fh.baselib.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.fh.baselib.R
import com.fh.baselib.utils.ToastUtil
import com.fh.baselib.widget.LoadingDialog
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.trello.rxlifecycle3.components.support.RxFragment
import gorden.rxbus2.RxBus
import kotlinx.android.synthetic.main.fragment_base.view.*

/**
 * Author: Austin
 * Date: 19-3-28
 * Description: Fragment基类
 */
abstract class BaseFragment : RxFragment(),View.OnClickListener , OnRefreshLoadMoreListener {
    private val BASE_VIEW_ID: Int = R.layout.fragment_base
    lateinit var mContext: Context
    lateinit var rootView: View
    //是否可见状态
    var lazyVisible: Boolean = false
    //标志位，标志Fragment已经初始化完成。
    private var isPrepared: Boolean = false
    //是否第一次加载完
    private var isFirstLoad = true
    lateinit var loadingDialog : LoadingDialog
    lateinit var smartRefreshLayout:SmartRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        rootView = inflater.inflate(initRootView(), container, false) as ViewGroup
        rootView = initRootView()
        this.mContext = rootView.context
        loadingDialog = LoadingDialog(mContext)
        RxBus.get().register(this)
        initView()
        initListener()
        initData()
        isPrepared = true
        lazyLoad()
        isFirstLoad = true
        return rootView
    }

    /**
     * 初始化跟布局
     */
    fun initRootView(): View {
        smartRefreshLayout = layoutInflater.inflate(BASE_VIEW_ID,null) as SmartRefreshLayout
        var subView: View = getLayoutInflater().inflate(layoutId(), null)
        var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
        subView.layoutParams = params
        smartRefreshLayout.layoutParams = params
        smartRefreshLayout.llyt_refresh.addView(subView)
        //是否启用下拉刷新功能
        smartRefreshLayout.setEnableRefresh(false)
        //是否启用上拉加载功能
        smartRefreshLayout.setEnableLoadMore(false)
        //关闭越界回弹功能
        smartRefreshLayout.setEnableOverScrollBounce(false)
        //禁止越界拖动
        smartRefreshLayout.setEnableOverScrollDrag(false)
        smartRefreshLayout.setOnRefreshLoadMoreListener(this)
        return smartRefreshLayout
    }

    fun autoRefresh() {
        smartRefreshLayout?.autoRefresh()
    }

    /**
     * 是否刷新布局
     */
    fun setRefreshView(bool: Boolean) {
        //是否启用下拉刷新功能
        smartRefreshLayout?.setEnableRefresh(bool)
    }

    /**
     * 是否刷新布局
     */
    fun setLoadMoreView(bool: Boolean) {
        //是否启用上拉加载功能
        smartRefreshLayout.setEnableLoadMore(bool)
    }

    /**
     * 加载更多结束
     */
    fun finishLoadMore() {
        smartRefreshLayout?.finishLoadMore()
    }

    /**
     * 下拉刷新结束
     */
    fun finishRefresh() {
        smartRefreshLayout?.finishRefresh()
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
     * 设置事件
     */
    abstract fun initListener()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 懒加载数据
     */
    public open fun lazyLoadData() {}

    protected fun onVisible() {
        lazyLoad()
    }

    protected fun onInvisible() {}

    protected fun lazyLoad() {
        if (!isPrepared || !isVisible) return
        //        isFirstLoad = false;
        lazyLoadData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            lazyVisible = true
            onVisible()
        } else {
            lazyVisible = false
            onInvisible()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            lazyVisible = true
            onVisible()
        } else {
            lazyVisible = false
            onInvisible()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        isPrepared = false
        RxBus.get().unRegister(this)
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
     * 添加Fragment
     */
    fun addFragment(viewId: Int,fragment: Fragment) {

        var fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(viewId,fragment)
        fragmentTransaction.commit()
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

    override fun onClick(v: View?) {

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }

    fun doActivity(path: String) {
    }


}
