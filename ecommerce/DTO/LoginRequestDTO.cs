﻿using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class LoginRequestDTO {
        [Required]
        [EmailAddress]
        public string Email { get; set; }
        [Required] 
        public string Password { get; set; }
    }
}