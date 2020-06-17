package com.jess.kakaopay.common.base

sealed class BaseState {
    class Toast(val message: String) : BaseState()
    class Progress(val isShow: Boolean) : BaseState()
}