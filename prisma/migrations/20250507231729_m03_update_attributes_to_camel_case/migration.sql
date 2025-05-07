/*
  Warnings:

  - The primary key for the `customer_addresses` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `address_id` on the `customer_addresses` table. All the data in the column will be lost.
  - You are about to drop the column `customer_id` on the `customer_addresses` table. All the data in the column will be lost.
  - You are about to drop the column `birth_date` on the `customers` table. All the data in the column will be lost.
  - You are about to drop the column `created_at` on the `customers` table. All the data in the column will be lost.
  - You are about to drop the column `first_name` on the `customers` table. All the data in the column will be lost.
  - You are about to drop the column `last_name` on the `customers` table. All the data in the column will be lost.
  - You are about to drop the column `phone_number` on the `customers` table. All the data in the column will be lost.
  - You are about to drop the column `updated_at` on the `customers` table. All the data in the column will be lost.
  - Added the required column `addressId` to the `customer_addresses` table without a default value. This is not possible if the table is not empty.
  - Added the required column `customerId` to the `customer_addresses` table without a default value. This is not possible if the table is not empty.
  - Added the required column `birthDate` to the `customers` table without a default value. This is not possible if the table is not empty.
  - Added the required column `firstName` to the `customers` table without a default value. This is not possible if the table is not empty.
  - Added the required column `lastName` to the `customers` table without a default value. This is not possible if the table is not empty.
  - Added the required column `phoneNumber` to the `customers` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "customer_addresses" DROP CONSTRAINT "customer_addresses_address_id_fkey";

-- DropForeignKey
ALTER TABLE "customer_addresses" DROP CONSTRAINT "customer_addresses_customer_id_fkey";

-- AlterTable
ALTER TABLE "customer_addresses" DROP CONSTRAINT "customer_addresses_pkey",
DROP COLUMN "address_id",
DROP COLUMN "customer_id",
ADD COLUMN     "addressId" INTEGER NOT NULL,
ADD COLUMN     "customerId" TEXT NOT NULL,
ADD CONSTRAINT "customer_addresses_pkey" PRIMARY KEY ("customerId", "addressId");

-- AlterTable
ALTER TABLE "customers" DROP COLUMN "birth_date",
DROP COLUMN "created_at",
DROP COLUMN "first_name",
DROP COLUMN "last_name",
DROP COLUMN "phone_number",
DROP COLUMN "updated_at",
ADD COLUMN     "birthDate" TIMESTAMP(3) NOT NULL,
ADD COLUMN     "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN     "firstName" TEXT NOT NULL,
ADD COLUMN     "lastName" TEXT NOT NULL,
ADD COLUMN     "phoneNumber" TEXT NOT NULL,
ADD COLUMN     "updatedAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- AddForeignKey
ALTER TABLE "customer_addresses" ADD CONSTRAINT "customer_addresses_customerId_fkey" FOREIGN KEY ("customerId") REFERENCES "customers"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "customer_addresses" ADD CONSTRAINT "customer_addresses_addressId_fkey" FOREIGN KEY ("addressId") REFERENCES "addresses"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
