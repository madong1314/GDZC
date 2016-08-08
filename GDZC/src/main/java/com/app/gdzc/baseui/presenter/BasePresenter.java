package com.app.gdzc.baseui.presenter;

//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//┃　　　┃  神兽保佑　　　　　　　　
//┃　　　┃  代码无BUG！
//┃　　　┗━━━┓
//┃　　　　　　　┣┓
//┃　　　　　　　┏┛
//┗┓┓┏━┳┓┏┛
// ┃┫┫　┃┫┫
// ┗┻┛　┗┻┛

/**
 * Created by 王少岩 on 2016/8/5.
 * 作用：之后的prsenter 都要继承该类
 */
public abstract class BasePresenter<V, M> {

    public V mView;
    public M mModel;

    /**
     * 关联view
     *
     * @param mView
     */
    public void attach(V mView) {
        this.mView = mView;
        mModel = generateModel();
    }

    //针对不同的presenter 生成对应的model
    protected abstract M generateModel();

    /**
     * 释放资源 tip:如果 有延迟操作 会出现空指针异常 请注意
     */
    public void dettach() {
    }
}
