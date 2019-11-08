package com.yyc.patient.ui.splash

import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.alibaba.android.arouter.facade.annotation.Route
import com.apeng.permissions.EsayPermissions
import com.apeng.permissions.OnPermission
import com.apeng.permissions.Permission
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.LogUtil
import com.fh.bdc.utils.CommonUtil
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import com.yyc.patient.R
import kotlinx.android.synthetic.main.activity_guide.*

@Route(path = RouteUrl.guide)
class GuideActivity : BaseActivity() {
    override fun layoutId(): Int {
        return  R.layout.activity_guide
    }

    override fun initView() {
        showToolbar(false)
        processLogic()
        initPermission()
    }

    override fun initData() {
    }
    private fun initPermission() {
        EsayPermissions.with(this)
//                .constantRequest()
//                .permission(Permission.WRITE_EXTERNAL_STORAGE,Permission.CAMERA,Permission.RECORD_AUDIO)
            .permission(
                Permission.RECORD_AUDIO, Permission.WRITE_EXTERNAL_STORAGE,
                Permission.READ_EXTERNAL_STORAGE
            )
            .request(object: OnPermission {
                override fun noPermission(denied: MutableList<String>?, quick: Boolean) {
                    if (quick) {
                        LogUtil.d("被永久拒绝权限")
                        EsayPermissions.gotoPermissionSettings(mContext);
                    } else {
                        LogUtil.d("暂时拒绝权限")
                    }
                }

                override fun hasPermission(granted: MutableList<String>?, isAll: Boolean) {
                    if (isAll) {
                        LogUtil.d("获取权限成功")
                    } else {
                        LogUtil.d("获取部分权限成功")
                    }
                }
            })
    }

    override fun isFullScreen(): Boolean {
        return true
    }


    override fun initListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        banner_guide.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, BGABanner.GuideDelegate {
            CommonUtil.saveFirstUse()
            finish()
            JumpUtil.jumpActivity(RouteUrl.login)
        })
    }

    private fun processLogic() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        // 设置数据源
        banner_guide.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
            R.drawable.ic_guide1,
            R.drawable.ic_guide2,
            R.drawable.ic_guide3
        )
    }

}
