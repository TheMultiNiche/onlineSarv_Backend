-- ==========================================
-- Sample Tool Data for PDF Tools Application
-- ==========================================

-- Clear existing data to avoid duplicates on restart
DELETE FROM user_activity;
DELETE FROM tools;

-- Reset auto-increment counter for tools table
ALTER TABLE tools AUTO_INCREMENT = 1;

-- Insert sample tools
INSERT INTO tools (tool_name, description, slug, is_active, created_at) VALUES
('Merge PDF', 'Combine multiple PDF files into one single document.', 'merge-pdf', TRUE, NOW()),
('Split PDF', 'Split a PDF into separate pages or custom page ranges.', 'split-pdf', TRUE, NOW()),
('Compress PDF', 'Reduce the file size of your PDF documents while maintaining quality.', 'compress-pdf', TRUE, NOW()),
('Convert PDF to Word', 'Convert your PDF documents into editable Word (.docx) format.', 'pdf-to-word', TRUE, NOW()),
('Convert Word to PDF', 'Convert Word documents (.docx) into PDF format.', 'word-to-pdf', TRUE, NOW()),
('PDF to Image', 'Convert PDF pages into high-quality image files.', 'pdf-to-image', TRUE, NOW()),
('Image to PDF', 'Combine multiple images into a single PDF document.', 'image-to-pdf', TRUE, NOW()),
('Rotate PDF', 'Rotate one or more pages in a PDF file to the desired orientation.', 'rotate-pdf', TRUE, NOW()),
('Unlock PDF', 'Remove password protection from secured PDF files.', 'unlock-pdf', TRUE, NOW()),
('Protect PDF', 'Add password protection and encryption to your PDF documents.', 'protect-pdf', TRUE, NOW()),
('Add Watermark', 'Apply custom text or image watermarks to your PDF files.', 'add-watermark', TRUE, NOW()),
('Organize PDF', 'Reorder, delete, or rearrange pages in your PDF files.', 'organize-pdf', TRUE, NOW()),
('Extract Pages', 'Extract specific pages from a PDF into a new file.', 'extract-pages', TRUE, NOW()),
('Repair PDF', 'Fix corrupted or unreadable PDF files.', 'repair-pdf', TRUE, NOW()),
('Edit PDF Metadata', 'Edit title, author, subject, and other metadata in a PDF.', 'edit-pdf-metadata', TRUE, NOW()),
('Sign PDF', 'Digitally sign your PDF files securely.', 'sign-pdf', TRUE, NOW());

-- Note: The 'tools' table will be automatically created by Hibernate based on the Tool entity
-- This script assumes the table structure matches the Tool entity definition
