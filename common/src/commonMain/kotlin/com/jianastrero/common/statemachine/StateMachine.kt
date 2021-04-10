package com.jianastrero.common.statemachine

import androidx.compose.runtime.Composable
import com.jianastrero.common.model.State

object StateMachine {

    private var currentController: Array<State>? = null
    private var currentIndex = -1

    @Composable
    fun start(controller: Array<State>) {
        currentIndex = -1
        currentController = controller
        nextState()
    }

    @Composable
    fun gotoState(state: State) {
        currentController?.let {
            if (it.contains(state)) {
                println("Go to state: ${state.name}")
                state.block()
            }
        }
    }

    @Composable
    fun nextState() {
        currentController?.let {
            currentIndex++
            if (currentIndex > it.size) {
                currentController = null
                currentIndex = -1
            } else {
                gotoState(it[currentIndex])
            }
        }
    }

    @Composable
    fun finish() {
        currentController = null
        currentIndex = -1
    }

}