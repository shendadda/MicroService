package com.shenme.androiddemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.login.LoginActivity;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.fragment.CategoryFragment;
import com.shenme.androiddemo.fragment.HomePageFragment;
import com.shenme.androiddemo.fragment.MineFragment;
import com.shenme.androiddemo.fragment.ShoppingCartFragment;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private static long BACK_PRESSED;
    public static final String BC_UPDATE_USER_INFO = "update_user_info";
    public static final String BC_UPDATE_ORDER_NUMBER = "update_order_number";
    public static final String BC_UPDATE_CURRENT_CITY = "update_current_city";
    public static final String GOTO_CART = "goto_cart";
    public static final String GOTO_CATEGORY = "goto_category";
    public static final String GOTO_MINE = "goto_mine";
    public static final String GOTO_HOME = "goto_home";
    private static final int IS_LOGIN = 5;
    private static final int IS_MINE_LOGIN = 7;
    private static final int IS_CART_LOGIN = 8;

    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private HomePageFragment homePageFragment;
    private CategoryFragment categoryFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor(R.color.black)
                .setInActiveColor(R.color.text_normal)
                .setBarBackgroundColor(R.color.white)
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_home, "首页").setInactiveIconResource(R.mipmap.icon_tab_home_grey))
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_category, "分类").setInactiveIconResource(R.mipmap.icon_tab_category_grey))
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_shopping_cart, "购物车").setInactiveIconResource(R.mipmap.icon_tab_shopping_cart_grey))
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_user, "个人中心").setInactiveIconResource(R.mipmap.icon_tab_user_grey))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    //默认fragment
    private void setDefaultFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        homePageFragment = new HomePageFragment();
        fragmentTransaction.add(R.id.fragment_container, homePageFragment);
        fragmentTransaction.commit();

    }

    /**
     * 隐藏fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homePageFragment != null) {
            fragmentTransaction.hide(homePageFragment);
        }
        if (categoryFragment != null) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (shoppingCartFragment != null) {
            fragmentTransaction.hide(shoppingCartFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }

    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (position) {
            case 0:
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    fragmentTransaction.add(R.id.fragment_container, homePageFragment);
                } else {
                    fragmentTransaction.show(homePageFragment);
                }
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                    fragmentTransaction.add(R.id.fragment_container, categoryFragment);
                } else {
                    fragmentTransaction.show(categoryFragment);
                }
                break;
            case 2:
                if (!Utils.isLoginUser(mContext)) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.putExtra(LoginActivity.IS_SHOPPING_CART_LOGIN, true);
                    startActivityForResult(intent, IS_CART_LOGIN);
                    return;
                } else {
                    if (shoppingCartFragment == null) {
                        shoppingCartFragment = new ShoppingCartFragment();
                        fragmentTransaction.add(R.id.fragment_container, shoppingCartFragment);
                    } else {
                        fragmentTransaction.show(shoppingCartFragment);
                    }
                }
                break;
            case 3:
                if (!Utils.isLoginUser(mContext)) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.putExtra(LoginActivity.IS_MINE_FRAGMENT_LOGIN, true);
                    startActivityForResult(intent, IS_MINE_LOGIN);
                    return;
                } else {
                    if (mineFragment == null) {
                        mineFragment = new MineFragment();
                        fragmentTransaction.add(R.id.fragment_container, mineFragment);
                    } else {
                        fragmentTransaction.show(mineFragment);
                    }

                }
                break;
        }
        fragmentTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IS_CART_LOGIN) {
                onTabSelected(2);
                return;
            }
            if (requestCode == IS_MINE_LOGIN) {
                onTabSelected(3);
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (BACK_PRESSED + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            ToastUtil.show("再按一次退出应用");
            BACK_PRESSED = System.currentTimeMillis();
        }
    }
}
