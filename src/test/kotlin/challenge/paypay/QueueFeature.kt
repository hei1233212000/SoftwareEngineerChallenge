package challenge.paypay

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object QueueFeature: Spek({
    Feature("Queue") {
        Scenario("adding item") {
            val originalQueue: Queue<String> = createNewQueue()
            lateinit var newQueue: Queue<String>

            Given("the original queue is empty at the very beginning") {
                originalQueue.isEmpty `should be equal to` true
            }

            When("adding something to the queue") {
                newQueue = originalQueue.enQueue("any item")
            }

            Then("the original queue is still empty as it is immutable") {
                originalQueue.isEmpty `should be equal to` true
            }

            And("the new queue should NOT be empty") {
                newQueue.isEmpty `should be equal to` false
            }

            And("the new queue should contain the new added item") {
                newQueue.head() `should be equal to` "any item"
            }
        }

        Scenario("removing item") {
            var originalQueue: Queue<String> = createNewQueue()
            lateinit var newQueue: Queue<String>

            Given("the queue has 2 items") {
                originalQueue = originalQueue.enQueue("first item")
                originalQueue = originalQueue.enQueue("second item")
            }

            When("removing the head item from the original queue") {
                newQueue = originalQueue.deQueue()
            }

            Then("the original queue should still contain same items as it is immutable") {
                originalQueue.head() `should be equal to` "first item"
            }

            And("the head item of the new queue should be \"second item\"") {
                newQueue.head() `should be equal to` "second item"
            }

            When("removing the head item from the new queue") {
                newQueue = newQueue.deQueue()
            }

            Then("the new queue should be empty now") {
                newQueue.isEmpty `should be equal to` true
            }
        }

        Scenario("get item from head in an empty queue") {
            val queue: Queue<String> = createNewQueue()
            var headItem: String? = null

            Given("the queue is empty at the very beginning") {
                queue.isEmpty `should be equal to` true
            }

            When("try to get the head item from the queue") {
                headItem = queue.head()
            }

            Then("the head item should be NULL") {
                headItem `should be` null
            }
        }

        Scenario("get item from head in a non-empty queue") {
            var queue: Queue<String> = createNewQueue()
            var headItem: String? = null

            Given("the queue has 2 items") {
                queue = queue.enQueue("first item")
                queue = queue.enQueue("second item")
            }

            When("try to get the head item from the queue") {
                headItem = queue.head()
            }

            Then("the head item should be equal to \"first item\"") {
                headItem `should be` "first item"
            }

            When("removing an item from queue") {
                queue = queue.deQueue()
            }

            When("try to get the head item from the queue again") {
                headItem = queue.head()
            }

            Then("the head item should be equal to \"second item\"") {
                headItem `should be` "second item"
            }
        }

        Scenario("check if the queue is empty") {
            var queue: Queue<String> = createNewQueue()

            Given("the queue is empty at the very beginning") {
                queue.isEmpty `should be equal to` true
            }

            When("adding item into the queue") {
                queue = queue.enQueue("any item")
            }

            Then("the queue is NOT empty now") {
                queue.isEmpty `should be equal to` false
            }

            When("removing item from the queue") {
                queue = queue.deQueue()
            }

            Then("the queue is empty now") {
                queue.isEmpty `should be equal to` true
            }
        }
    }
})

private fun createNewQueue(): Queue<String> = ImmutableQueue(String::class.java)
