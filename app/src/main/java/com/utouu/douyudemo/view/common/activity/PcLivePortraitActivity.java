package com.utouu.douyudemo.view.common.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.flyco.tablayout.SlidingTabLayout;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseActivity;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.common.CommonPcLiveVideoModelLogic;
import com.utouu.douyudemo.model.logic.common.bean.OldLiveVideoInfo;
import com.utouu.douyudemo.presenter.common.impl.CommonPcLiveVideoPresenterImp;
import com.utouu.douyudemo.presenter.common.interfaces.CommonPcLiveVideoContract;
import com.utouu.douyudemo.ui.loadplay.LoadingView;
import com.utouu.douyudemo.utils.ToastUtils;
import com.utouu.douyudemo.view.LoadDataView;
import com.utouu.douyudemo.view.TestFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;
/**
 * Create by 黄思程 on 2017/4/19  17:16
 * Function：
 * Desc：视频直播竖屏页面
 */
public class PcLivePortraitActivity extends BaseActivity<CommonPcLiveVideoModelLogic, CommonPcLiveVideoPresenterImp>
        implements CommonPcLiveVideoContract.View, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnErrorListener {

    @BindView(R.id.roomLive_progress)
    TextView roomLiveProgress;
    @BindView(R.id.roomLive_roomId)
    TextView roomLiveRoomId;
    @BindView(R.id.roomLive_date)
    TextView roomLiveDate;
    @BindView(R.id.roomLive_personCount)
    TextView roomLivePersonCount;
    @BindView(R.id.roomLive_roomId1)
    TextView roomLiveRoomId1;
    @BindView(R.id.roomLive_inputText)
    EditText roomLiveInputText;
    @BindView(R.id.roomLive_roomTitle)
    TextView roomLiveRoomTitle;

    @BindView(R.id.roomLive_roomShare)
    ImageView roomLiveRoomShare;
    @BindView(R.id.roomLive_sendMsg)
    ImageView roomLiveSendMsg;
    @BindView(R.id.roomLive_charge)
    ImageView roomLiveCharge;
    @BindView(R.id.roomLive_shop)
    ImageView roomLiveShop;
    @BindView(R.id.roomLive_gift)
    ImageView roomLiveGift;
    @BindView(R.id.roomLive_report)
    ImageView roomLiveReport;
    @BindView(R.id.roomLive_effect)
    ImageView roomLiveEffect;
    @BindView(R.id.roomLive_play)
    ImageView roomLivePlay;
    @BindView(R.id.roomLive_fullEnter)
    ImageView roomLiveFullEnter;
    @BindView(R.id.roomLive_back)
    ImageView roomLiveBack;

    @BindView(R.id.roomLive_roomLine)
    View roomLiveRoomLine;
    @BindView(R.id.roomLive_roomInfo)
    LinearLayout roomLiveRoomInfo;
    @BindView(R.id.roomLive_inputLayout)
    LinearLayout roomLiveInputLayout;
    @BindView(R.id.roomLive_roomPageHead)
    SlidingTabLayout roomLiveRoomPageHead;
    @BindView(R.id.roomLive_roomPageContainer)
    ViewPager roomLiveRoomPageContainer;
    @BindView(R.id.roomLive_video)
    VideoView roomLiveVideo;
    @BindView(R.id.roomLive_loadingView)
    LoadingView roomLiveLoadingView;

    private OldLiveVideoInfo videoInfo;

    private String Room_id;  //房间id
    private SVProgressHUD svProgressHUD;
    private CountDownTimer downTimer;

    @Override
    protected int getLayoutId() {
        Vitamio.isInitialized(this);
        return R.layout.layout_pc_live_portrait;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        Room_id = getIntent().getExtras().getString("Room_id");
        roomLiveVideo.setKeepScreenOn(true);
        mPresenter.getPresenterPcLiveVideoInfo(Room_id);

        svProgressHUD = new SVProgressHUD(this);
        showOrHideIcon(true);

        roomLiveRoomPageContainer.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TestFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        roomLiveRoomPageHead.setViewPager(roomLiveRoomPageContainer,new String[]{"聊天","主播","排行榜","贵族","直播"});
    }

    @Override
    protected void onEvent() {
        roomLiveVideo.setOnInfoListener(this);
        roomLiveVideo.setOnBufferingUpdateListener(this);
        roomLiveVideo.setOnErrorListener(this);
    }

    @Override
    protected BaseView getView() {
        return this;
    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    public void showErrorWithStatus(String msg) {
        runOnUiThread(() -> svProgressHUD.showErrorWithStatus("主播还在赶来的路上~~"));
    }

    @Override
    public void getViewPcLiveVideoInfo(OldLiveVideoInfo mLiveVideoInfo) {
        runOnUiThread(() -> {
            videoInfo = mLiveVideoInfo;
            getViewInfo(mLiveVideoInfo);
        });
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        roomLiveLoadingView.setVisibility(View.VISIBLE);
        roomLiveProgress.setVisibility(View.VISIBLE);
        if (roomLiveVideo.isPlaying())
            roomLiveVideo.pause();
        roomLiveProgress.setText("直播已缓冲" + percent + "%...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getPresenterPcLiveVideoInfo(Room_id);
        if (roomLiveVideo != null) {
            roomLiveVideo.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (roomLiveVideo != null) {
            roomLiveVideo.pause();
        }
        downTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        if (roomLiveVideo != null) {
            //释放资源
            roomLiveVideo.stopPlayback();
        }
        super.onDestroy();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
            svProgressHUD.showErrorWithStatus("主播还在赶来的路上~~");
        }
        return true;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (roomLiveVideo.isPlaying()) {
                    roomLiveVideo.pause();
                }
                roomLivePlay.setImageResource(R.drawable.img_live_videoplay);
                break;
//            完成缓冲
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                roomLiveLoadingView.setVisibility(View.INVISIBLE);
                roomLiveProgress.setVisibility(View.INVISIBLE);
                if (!roomLiveVideo.isPlaying())
                    roomLiveVideo.start();
                showOrHideIcon(false);
                roomLivePlay.setImageResource(R.drawable.img_live_videopause);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:

                break;
        }
        return true;
    }

    /**
     * 获得房间信息
     *
     * @param mLiveVideoInfo
     */
    private void getViewInfo(OldLiveVideoInfo mLiveVideoInfo) {
        String url = mLiveVideoInfo.getData().getLive_url();
        Uri uri = Uri.parse(url);
        if (roomLiveRoomTitle != null) {
            roomLiveRoomTitle.setText("");
        }
        if (roomLiveVideo != null) {
            roomLiveVideo.setVideoURI(uri);
            roomLiveVideo.setBufferSize(1024 * 1024 * 2);
            roomLiveVideo.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH,1);
            roomLiveVideo.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
            roomLiveVideo.requestFocus();
            roomLiveVideo.setOnPreparedListener(mediaPlayer -> {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
                roomLivePlay.setImageResource(R.drawable.img_live_videopause);
            });
        }
    }


    @OnClick({R.id.roomLive_videoLayout, R.id.roomLive_back, R.id.roomLive_report, R.id.roomLive_effect,
            R.id.roomLive_play, R.id.roomLive_fullEnter, R.id.roomLive_roomShare,
            R.id.roomLive_sendMsg, R.id.roomLive_charge, R.id.roomLive_shop, R.id.roomLive_gift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.roomLive_videoLayout:
                delayHideIcon(3000);
                break;
            case R.id.roomLive_back:
                finish();
                break;
            case R.id.roomLive_report:
                ToastUtils.showShort(this,"正在开发");
                break;
            case R.id.roomLive_effect:
                ToastUtils.showShort(this,"正在开发");
                break;
            case R.id.roomLive_play:
                if (roomLiveVideo.isPlaying()) {
                    roomLiveVideo.pause();
                    roomLivePlay.setImageResource(R.drawable.img_live_videoplay);
                } else {
                    roomLiveVideo.start();
                    roomLivePlay.setImageResource(R.drawable.img_live_videopause);
                }
                break;
            case R.id.roomLive_fullEnter:
                Intent intent = new Intent(this, PcLiveVideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Room_id", videoInfo.getData().getRoom_id());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.roomLive_roomShare:
                ToastUtils.showShort(this,"正在开发");
                break;
            case R.id.roomLive_sendMsg:
                roomLiveInputText.setText("");
                break;
            case R.id.roomLive_charge:
                ToastUtils.showShort(this,"正在开发");
                break;
            case R.id.roomLive_shop:
                ToastUtils.showShort(this,"正在开发");
                break;
            case R.id.roomLive_gift:
                ToastUtils.showShort(this,"正在开发");
                break;
        }
    }

    /**
     * Icon点击延时隐藏
     * @param timeMills
     */
    private void delayHideIcon(long timeMills) {
        showOrHideIcon(true);
        downTimer = new CountDownTimer(timeMills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                showOrHideIcon(false);
            }
        }.start();
    }

    /**
     * 控制是否显示或者隐藏相关Icon
     * @param isShow
     */
    public void showOrHideIcon(boolean isShow){
        roomLiveRoomInfo.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        roomLiveBack.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        roomLiveReport.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        roomLiveEffect.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        roomLivePlay.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        roomLiveFullEnter.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        roomLiveRoomId.setVisibility(!isShow?View.VISIBLE:View.GONE);
        roomLiveLoadingView.setVisibility(View.INVISIBLE);
        roomLiveProgress.setVisibility(View.INVISIBLE);
    }
}
