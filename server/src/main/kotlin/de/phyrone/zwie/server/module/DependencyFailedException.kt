package de.phyrone.zwie.server.module

class DependencyFailedException(dependency: String, throwable: Throwable) :
    Exception("Dependency $dependency failed", throwable)