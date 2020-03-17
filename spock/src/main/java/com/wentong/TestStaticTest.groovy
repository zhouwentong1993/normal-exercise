package com.wentong

import spock.lang.Specification

class TestStaticTest extends Specification {
    def "GetStatic"() {
        given:
        def filesClass = GroovySpy(TestStatic, global: true)
        TestStatic.mockStatic() >> "haha"
        expect:
        println TestStatic.mockStatic()

    }
}
