package com.wentong

import spock.lang.Specification

class FirstSpec extends Specification {

    def "sum should return param1+param2"() {
        given:
        def content = new Content(name: "01")
        content.name = "02"
        content.name = "03"
        println(content.name)
        when:
        println()
        then:
        println(old(content.name))
        println(old(content.name))
        println(old(content.name))
        println(old(content.name))
    }

    def "test old method"() {
        given:
        final StringBuilder builder = new StringBuilder('Spock ')

        when:
        builder << appendValue

        then:
        builder.toString() == old(builder.toString()) + appendValue

        where:
        appendValue << ['rocks!', 'is fun.', 'amazes.']
    }

}
