package com.yyc.patient.ui.home

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.base.BaseFragment
import com.fh.baselib.utils.TabUtils
import com.fh.bdc.utils.RouteUrl
import com.google.android.material.tabs.TabLayout
import com.yyc.patient.R
import kotlinx.android.synthetic.main.activity_home.*

@Route(path = RouteUrl.home)
class HomeActivity : BaseActivity() , ViewPager.OnPageChangeListener{

    private  var tabTitles = mutableListOf(R.string.tab1, R.string.tab1, R.string.tab1,
        R.string.tab1)
    private  var tabImgs = mutableListOf(R.drawable.tab4_selector, R.drawable.tab4_selector, R.drawable.tab4_selector,
        R.drawable.tab4_selector)
    private  var tabFragments = mutableListOf<BaseFragment>()
    private lateinit var homePagerAdapter: HomePagerAdapter

    override fun layoutId(): Int {
        return R.layout.activity_home

    }

    override fun initView() {
        showToolbar(false)

        tabFragments.add(HomeFragment())
        tabFragments.add(MineFragment())
        tabFragments.add(MineFragment())
        tabFragments.add(MineFragment())


        homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        viewpager.adapter = homePagerAdapter
        TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles,tabImgs)
        homePagerAdapter.notifyDataSetChanged()




    }

    override fun initData() {

    }

    override fun initListener() {
        viewpager.setScroll(true)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))
        viewpager.setOnPageChangeListener(this)
        viewpager.offscreenPageLimit = 3

    }

    /**
     * @description: ViewPager 适配器
     */
    inner class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return tabFragments.get(position)
        }

        override fun getCount(): Int {
            return tabFragments.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }
}
