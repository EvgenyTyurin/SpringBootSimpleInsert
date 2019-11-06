package evgenyt.tacos.data;

import evgenyt.tacos.Order;

/**
 * Work with Order table
 */

public interface OrderRepository {
	Order save(Order order);
}
