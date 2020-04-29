package com.danny.suppernotes.common




/**
 * Why use a base class? To both share implementation (properties and functions), and enforce a contract (interface) for all listener classes
 */
abstract class BaseLogic(val dispatcher: DispatcherProvider) {

    protected lateinit var jobTracker: Job
}