using ecommerce.DTO;
using ecommerce.Models;
using ecommerce.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ecommerce.Controllers {
    [Route("api/vendor")]
    [ApiController]
    public class VendorController : ControllerBase {

        private readonly VendorService _vendorService;

        public VendorController(VendorService vendorService) { 
            _vendorService = vendorService;
        }

        [HttpPost]
        public async Task<ActionResult<Vendor>> CreateVendor([FromBody] CreateVendorRequestDTO vendorDTO) {
            if (!ModelState.IsValid) {
                return BadRequest(ModelState);
            }

            Vendor vendor = await _vendorService.CreateVendor(vendorDTO);

            if (vendor == null) {
                return Conflict(new { message = "O e-mail informado já está em uso. Por favor, escolha outro." });
            }

            return CreatedAtAction(nameof(GetVendorById), new { id = vendor.Id }, vendor);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Vendor>> GetVendorById(long id) {
            return Ok(await _vendorService.GetVendorById(id));
        }

        [HttpGet]
        public async Task<ActionResult<Vendor>> GetAllVendors() {
            return Ok(await _vendorService.GetAllVendors());
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteVendor(int id) {
            await _vendorService.DeleteVendor(id);
            return NoContent();
        }
    }
}
