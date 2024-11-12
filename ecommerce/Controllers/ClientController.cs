using ecommerce.DTO;
using ecommerce.Models;
using ecommerce.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ecommerce.Controllers {
    [Route("api/[client]")]
    [ApiController]
    public class ClientController : ControllerBase {
        private readonly ClientService _clientService;

        public ClientController(ClientService clientService) {
            _clientService = clientService;
        }

        [HttpPost]
        public async Task<ActionResult<Client>> CreateClient([FromBody] CreateClientRequestDTO clientDTO) {
            if (!ModelState.IsValid) {
                return BadRequest(ModelState);
            }

            Client createdClient = await _clientService.CreateClient(clientDTO);

            return CreatedAtAction(nameof(GetClientById), new { id = createdClient.Id }, createdClient);
        }

        [HttpGet]
        public async Task<ActionResult<Client>> GetAllClients() {
            return Ok(await _clientService.GetAllClients());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Client>> GetClientById(int id) {
            var client = await _clientService.GetClientById(id);

            if (client == null) {
                return NotFound();
            }

            return Ok(client);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<Client>> UpdateClient(int id, [FromBody] UpdateClientRequestDTO clientDTO) {
            try {
                var updatedClient = await _clientService.UpdateClient(clientDTO, id);
                return Ok(updatedClient); 
            } catch (KeyNotFoundException) {
                return NotFound(); 
            }
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteClient(int id) {
            await _clientService.DeleteClient(id);
            return NoContent();
        }
    }
}