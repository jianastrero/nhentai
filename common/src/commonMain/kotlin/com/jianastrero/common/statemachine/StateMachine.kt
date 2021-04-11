package com.jianastrero.common.statemachine

import androidx.compose.runtime.*
import com.jianastrero.common.model.State
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object StateMachine {

    private var currentController: Array<State>? = null
    private var currentIndex = -1
    private var updateState = false

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
                updateState = true
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
                finish()
            } else {
                gotoState(it[currentIndex])
            }
        }
    }

    @Composable
    fun finish() {
        println("Finish at state: ${currentController?.get(currentIndex)?.name}")
        currentController = null
        currentIndex = -1
    }

    @Composable
    fun stateHolder() {

        var state by remember { mutableStateOf<State?>(null) }

        GlobalScope.launch {
            delay(200)
            while (true) {
                if (updateState && currentIndex != -1) {
                    state = currentController?.get(currentIndex)
                }
                delay(200)
            }
        }

        if (state != null) {
            state?.block?.invoke()
        }
    }

}