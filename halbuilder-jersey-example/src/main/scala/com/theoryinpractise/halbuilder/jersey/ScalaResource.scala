package com.theoryinpractise.halbuilder.jersey

import javax.ws.rs._
import com.theoryinpractise.halbuilder.api.{Representation, RepresentationFactory}
import com.theoryinpractise.halbuilder.jersey.Main._

@Path("queue")
class ScalaResource {

  /**
   * A simple resource that consumes a HAL Representation
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(Array(RepresentationFactory.HAL_JSON, RepresentationFactory.HAL_XML))
  def getIt(): Representation = {
    representationFactory.newRepresentation(mkUri(classOf[ScalaResource]))
      .withProperty("message", "Hmmm")
  }

}
