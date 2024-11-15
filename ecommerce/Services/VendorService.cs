﻿using ecommerce.Database;
using ecommerce.DTO;
using ecommerce.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace ecommerce.Services {
    public class VendorService {
        private readonly ApplicationDbContext _context;
        
        public VendorService(ApplicationDbContext context) { 
            _context = context;
        }

        public async Task<Vendor> CreateVendor(CreateVendorRequestDTO vendorDTO) {
            var vendor = new Vendor(vendorDTO.Name, vendorDTO.Email, vendorDTO.PhoneNumber);

            bool emailExists = await _context.Vendors.AnyAsync(v => v.Email == vendorDTO.Email);

            if (emailExists) {
                return null;
            }

            _context.Vendors.Add(vendor);
            await _context.SaveChangesAsync();
            return vendor;
        }

        public async Task<Vendor> GetVendorById(long id) {
            return await _context.Vendors.FindAsync(id);
        }

        public async Task<List<Vendor>> GetAllVendors() {
            return await _context.Vendors.ToListAsync();
        }

        public async Task<Vendor> UpdateVendor(int id, UpdateVendorRequestDTO vendorDTO) {
            var vendor = await _context.Vendors.FindAsync(id) ?? throw new KeyNotFoundException("Vendor not found");
            vendor.Name = vendorDTO.Name ?? vendor.Name;
            vendor.Email = vendorDTO.Email ?? vendor.Email;
            vendor.PhoneNumber = vendorDTO.PhoneNumber ?? vendor.PhoneNumber;

            _context.SaveChangesAsync();
            return vendor;
        }

        public async Task<bool> DeleteVendor(int id) {
            var vendor = await _context.Vendors.FindAsync(id) ?? throw new KeyNotFoundException("Vendor not found");

            _context.Vendors.Remove(vendor);
            _context.SaveChangesAsync();
            return true;
        }
    }
}
