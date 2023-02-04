package de.phyrone.zwie.shared.common.events


/*
 * Lifecycle of Delivery
 *   - ASYNC PRE DELIVERY (unordered, not cancelable, async)
 *     - f.e. used for await
 *   - DELIVERY (ordered,cancelable
 *   - ASYNC POST DELIVERY (unordered, not cancelable, async)
 */
interface EventDelivery {

}