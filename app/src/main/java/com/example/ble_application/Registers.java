package com.example.ble_application;

public class Registers {
        public  final byte[] buffer4 = {(byte) 0x78, (byte) 0x32, (byte) 0x31, (byte) 0x11};
        public  final byte[] buffer5 = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03}; // Write Allow
        public  final byte[] buffer6 = {(byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x2A};
        public  final byte[] buffer7 = {(byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x6A};
        public  final byte[] buffer8 = {(byte) 0x8B, (byte) 0xFE, (byte) 0x79, (byte) 0xC0};

        public  final byte[] volume0 = {(byte) 0x00, (byte) 0x04, (byte) 0xA2, (byte) 0x8A};
        public  final byte[] volume1 = {(byte) 0x00, (byte) 0x04, (byte) 0x92, (byte) 0x49};
        public  final byte[] volume2 = {(byte) 0x00, (byte) 0x04, (byte) 0x82, (byte) 0x08};
        public  final byte[] volume3 = {(byte) 0x00, (byte) 0x04, (byte) 0x71, (byte) 0xC7};
        public  final byte[] volume4 = {(byte) 0x00, (byte) 0x04, (byte) 0x61, (byte) 0x86};
        public  final byte[] volume5 = {(byte) 0x00, (byte) 0x04, (byte) 0x51, (byte) 0x45};
        public  final byte[] volume6 = {(byte) 0x00, (byte) 0x04, (byte) 0x41, (byte) 0x04};
        public  final byte[] volume7 = {(byte) 0x00, (byte) 0x04, (byte) 0x30, (byte) 0xC3};
        public  final byte[] volume8 = {(byte) 0x00, (byte) 0x04, (byte) 0x20, (byte) 0x82};
        public  final byte[] volume9 = {(byte) 0x00, (byte) 0x04, (byte) 0x10, (byte) 0x41};
        public  final byte[] volume10 = {(byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00};

        // اگر لیست لازم داشته باشی:
        public  final byte[][] volumeList = {
                volume0, volume1, volume2, volume3, volume4,
                volume5, volume6, volume7, volume8, volume9, volume10
        };
}
