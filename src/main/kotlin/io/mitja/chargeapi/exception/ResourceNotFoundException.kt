package io.mitja.chargeapi.exception

import java.lang.RuntimeException

class ResourceNotFoundException(message: String) : RuntimeException(message)
