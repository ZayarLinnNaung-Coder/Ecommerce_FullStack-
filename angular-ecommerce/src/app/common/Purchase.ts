import {Customer} from "./Customer";
import {Address} from "./Address";
import {Order} from "./Order";
import {OrderItem} from "./OrderItem";

export class Purchase{
  customer: Customer;
  shippingAddress: Address;
  billingAddress: Address;
  order: Order;
  orderItems: OrderItem[];
}
