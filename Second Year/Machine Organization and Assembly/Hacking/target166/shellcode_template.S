
# https://www.redhat.com/en/blog/linkers-warnings-about-executable-stacks-and-segments
.section  .note.GNU-stack, "x", @progbits

.text
.global main
main:

    # Place your payload assembly instructions in here.
   
    # Then compile with: 
    # $ gcc shellcode_file.S -no-pie -o shellcode_file.out

    # The above command will make a small program that houses your specific
    # assembly instructions' bytecode values. 

    # Finally, generate your desired instruction byte values:
    # $ objdump -d ./shellcode_file.out > shellcode_file.asm

    # Find the <main> function and lift those instruction byte values.
    # Place them in your buffer input text file to inject executable code into
    # the vulnerable buffer.

    # Use the classic x86-64 return address hack to "return" to the start of 
    # your injected code in the buffer, hijacking the CPU..!
