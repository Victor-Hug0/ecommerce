using ecommerce.DTO;
using ecommerce.Models;
using ecommerce.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Mvc;
using NuGet.Protocol.Plugins;
using System.Security.Claims;
using System.Text.Json.Serialization;
using System.Text.Json;

namespace ecommerce.Controllers {
    [Route("api/client")]
    [ApiController]
    public class ClientController : ControllerBase {
        private readonly ClientService _clientService;

        public ClientController(ClientService clientService) {
            _clientService = clientService;
        }

        [HttpPost("register")]
        public async Task<ActionResult<Client>> CreateClient([FromBody] CreateClientRequestDTO clientDTO) {
            try {
                var createdClient = await _clientService.CreateClient(clientDTO);

                if (createdClient == null) {
                    return Conflict(new { message = "O e-mail informado já está em uso. Por favor, escolha outro." });
                }

                return CreatedAtAction(nameof(GetClientById), new { id = createdClient.Id }, createdClient);
            } catch (Exception ex) {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginRequestDTO loginDTO) {
            try {
                var token = await _clientService.Login(loginDTO);
                return Ok(new { token });
            } catch (ArgumentException ex) {
                return BadRequest(new { error = ex.Message });
            } catch (UnauthorizedAccessException ex) {
                return Unauthorized(new { error = ex.Message });
            }
        }

        [HttpGet]
        public async Task<ActionResult<List<ClientResponseDTO>>> GetAllClients() {
            return Ok(await _clientService.GetAllClients());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<ClientResponseDTO>> GetClientById(long id) {
            var client = await _clientService.GetClientById(id);

            if (client == null) {
                return NotFound();
            }

            return Ok(client);
        }
        
        [HttpPut("{id}")]
        public async Task<ActionResult<Client>> UpdateClient(long id, [FromBody] UpdateClientRequestDTO clientDTO) {
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

        [HttpPost("review")]
        [Authorize] 
        public async Task<IActionResult> CreateReview([FromBody] ReviewProductRequestDTO reviewDTO) {
            try {
                var review = await _clientService.CreateReview(reviewDTO);
                return Ok(new { message = "Avaliação criada com sucesso" });
            } catch (KeyNotFoundException ex) {
                return NotFound(new { error = ex.Message });
            } catch (InvalidOperationException ex) {
                return BadRequest(new { error = ex.Message });
            } catch (UnauthorizedAccessException ex) {
                return Unauthorized(new { error = ex.Message });
            }
        }
    }
}