package com.jianastrero.common.controller

import com.jianastrero.common.model.State
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.ui.ServerUpdatedError

val SERVER_UPDATED = State("SERVER_UPDATED") {
    ServerUpdatedError()
    StateMachine.finish()
}

val SERVER_UPDATED_ERROR_CONTROLLER = arrayOf(
    SERVER_UPDATED
)