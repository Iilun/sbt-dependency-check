sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("io.github.iilun" % "sbt-dependency-check" % x)
  case _ => sys.error(
    """|The system property 'plugin.version' is not defined.
       |Specify this property using the sbt parameter -D.""".stripMargin)
}
