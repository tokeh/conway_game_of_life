package com.tokeh.gameoflife

import com.google.inject.AbstractModule
import com.tokeh.gameoflife.controller.Controller
import com.tokeh.gameoflife.controller.impl.ControllerImpl
import com.tokeh.gameoflife.model.impl.GridFactoryImpl
import com.tokeh.gameoflife.model.GridFactory
import net.codingwell.scalaguice.ScalaModule

class BaseModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[Controller].to[ControllerImpl]
    bind[GridFactory].to[GridFactoryImpl]
  }
}
