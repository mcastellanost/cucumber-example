Feature: Base
    @no-docker
    Scenario: test without docker
        Given the input path "src/test/resources/input.txt"
        Given the output path "src/test/resources/output.txt"
        When execute the program
        Then the exitcode should be 0
        Then the excepted output should be equal to 6
        Then the excepted output should not be equal to 7
        Then the excepted output should be less than 10

    @docker
    Scenario: test without docker