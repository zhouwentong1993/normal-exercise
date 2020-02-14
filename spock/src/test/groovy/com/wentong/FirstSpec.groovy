package com.wentong

import spock.lang.Specification

class FirstSpec extends Specification {

    def "sum should return param1+param2"() {
        expect:
        sum.sum(1, 1) == 2
    }

}
