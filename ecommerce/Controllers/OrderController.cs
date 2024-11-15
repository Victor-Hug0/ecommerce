using ecommerce.DTO;
using ecommerce.Models;
using ecommerce.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ecommerce.Controllers {
    [Route("api/order")]
    [ApiController]
    public class OrderController : ControllerBase {
        private readonly OrderService _orderService;

        public OrderController(OrderService orderService) { 
            _orderService = orderService;
        }

        [HttpPost]
        public async Task<ActionResult<Order>> CreateOrder([FromBody] CreateOrderRequestDTO orderDTO) {
            try {
                var order = await _orderService.CreateOrder(orderDTO);
                return Ok(order);
            } catch (UnauthorizedAccessException ex) {
                return Unauthorized(new { error = ex.Message });
            } catch (Exception ex) {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpPost("pay/{orderId}")]
        [Authorize]
        public async Task<IActionResult> PayOrder(long orderId, [FromBody] PaymentDTO paymentDTO) {
            var result = await _orderService.PayOrder(orderId, paymentDTO);

            return result;
        }
    }
}
