	# All program code is placed after the
	# .text assembler directive
	.text

	# Declare main as a global function
	.globl	main

fun_add:
	# Registers used in this function: [$s0, $s1, $s2]
	# Store registers
	sw $s0, -24($sp)
	sw $s1, -20($sp)
	sw $s2, -16($sp)
	addi	 $sp, $sp, -24	#Move the stack pointer for the compound statement
	lw	$s0, 16($sp)	#Load the mutable from the stack to a register for use.
	lw	$s1, 12($sp)	#Load the mutable from the stack to a register for use.
	add	$s2, $s0, $s1	#Evaluate binary operation
	addi	$sp, 24		#Reset the offset of the stack pointer
	sw	$s2, -4($sp)	#Store the return result on the stack
	lw $s0, -24($sp)
	lw $s1, -20($sp)
	lw $s2, -16($sp)
	jr	$ra		#Return to caller
	addi	 $sp, $sp, 24	#Reset the stack pointer when leaving the compound statement
	lw $s0, -24($sp)
	lw $s1, -20($sp)
	lw $s2, -16($sp)
	jr	$ra		#Return to caller
	# Restore registers
	lw $s0, -24($sp)
	lw $s1, -20($sp)
	lw $s2, -16($sp)
main:
	# Registers used in this function: [$s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $s8]
	# Store registers
	sw $s0, -36($sp)
	sw $s1, -32($sp)
	sw $s2, -28($sp)
	sw $s3, -24($sp)
	sw $s4, -20($sp)
	sw $s5, -16($sp)
	sw $s6, -12($sp)
	sw $s7, -8($sp)
	sw $s8, -4($sp)
	addi	 $sp, $sp, -36	#Move the stack pointer for the compound statement
	lw	$s0, -8($sp)	#Load the mutable from the stack to a register for use.
	li	$s1, 0
	move	$s0, $s1	#Store rhs into mutable
	sw	$s0, -8($sp)	#Store the mutable value on the stack.
	li	$s0, 1
	move	$a0, $s0	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	lw	$s0, -8($sp)	#Load the mutable from the stack to a register for use.
	move	$a0, $s0	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	la	$a0, label3	#Load the string's address in data.
	li	$v0, 4		#Print string
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	li	$s0, 1
	move	$a0, $s0	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	lw	$s0, 36($sp)	#Load the mutable from the stack to a register for use.
	li	$s1, 2
	move	$s0, $s1	#Store rhs into mutable
	sw	$s0, 36($sp)	#Store the mutable value on the stack.
	lw	$s0, -4($sp)	#Load the mutable from the stack to a register for use.
	li	$s1, 3
	move	$s0, $s1	#Store rhs into mutable
	sw	$s0, -4($sp)	#Store the mutable value on the stack.
	lw	$s0, 36($sp)	#Load the mutable from the stack to a register for use.
	move	$a0, $s0	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	lw	$s0, -4($sp)	#Load the mutable from the stack to a register for use.
	move	$a0, $s0	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	addi	 $sp, $sp, -8	#Move the stack pointer for the compound statement
	lw	$s0, -4($sp)	#Load the mutable from the stack to a register for use.
	lw	$s1, 44($sp)	#Load the mutable from the stack to a register for use.
	lw	$s2, 4($sp)	#Load the mutable from the stack to a register for use.
	add	$s3, $s1, $s2	#Evaluate binary operation
	li	$s1, 1
	add	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 2
	add	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 3
	add	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 4
	add	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 5
	add	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 6
	add	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 7
	add	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 8
	add	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 9
	add	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 10
	add	$s3, $s2, $s1	#Evaluate binary operation
	lw	$s1, 44($sp)	#Load the mutable from the stack to a register for use.
	sub	$s2, $s3, $s1	#Evaluate binary operation
	lw	$s1, 4($sp)	#Load the mutable from the stack to a register for use.
	sub	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 1
	sub	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 2
	sub	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 3
	sub	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 4
	sub	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 5
	sub	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 6
	sub	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 7
	sub	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 8
	sub	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 9
	sub	$s2, $s3, $s1	#Evaluate binary operation
	li	$s1, 10
	sub	$s3, $s2, $s1	#Evaluate binary operation
	li	$s1, 4
	add	$s2, $s3, $s1	#Evaluate binary operation
	move	$s0, $s2	#Store rhs into mutable
	sw	$s0, -4($sp)	#Store the mutable value on the stack.
	lw	$s0, -4($sp)	#Load the mutable from the stack to a register for use.
	move	$a0, $s0	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	addi	 $sp, $sp, 8	#Reset the stack pointer when leaving the compound statement
	li	$s0, 3
	li	$s1, 1
	li	$s2, 1
	add	$s3, $s1, $s2	#Evaluate binary operation
	add	$s1, $s0, $s3	#Evaluate binary operation
	move	$a0, $s1	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	li	$s0, 3
	li	$s1, 4
	li	$s2, 1
	sub	$s3, $s1, $s2	#Evaluate binary operation
	add	$s1, $s0, $s3	#Evaluate binary operation
	move	$a0, $s1	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	li	$s0, 3
	li	$s1, 2
	li	$s2, 2
	mul	$s3, $s1, $s2	#Evaluate binary operation
	add	$s1, $s0, $s3	#Evaluate binary operation
	move	$a0, $s1	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	li	$s0, 3
	li	$s1, 10
	li	$s2, 2
	div	$s3, $s1, $s2	#Evaluate binary operation
	add	$s1, $s0, $s3	#Evaluate binary operation
	move	$a0, $s1	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	li	$s0, 3
	li	$s1, 16
	li	$s2, 10
	rem	$s3, $s1, $s2	#Evaluate binary operation
	add	$s1, $s0, $s3	#Evaluate binary operation
	move	$a0, $s1	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	li	$s0, 1
	li	$s1, 3
	li	$s2, 2
	sw	$s1, -8($sp)	#Push parameter onto the stack
	sw	$s2, -12($sp)	#Push parameter onto the stack
	jal	fun_add		#Call the function.
	lw	$s3, -4($sp)	#Return the value of the function call
	sw	$s0, -8($sp)	#Push parameter onto the stack
	sw	$s3, -12($sp)	#Push parameter onto the stack
	jal	fun_add		#Call the function.
	lw	$s4, -4($sp)	#Return the value of the function call
	li	$s5, 2
	add	$s6, $s4, $s5	#Evaluate binary operation
	li	$s4, 1
	li	$s5, 1
	sw	$s4, -8($sp)	#Push parameter onto the stack
	sw	$s5, -12($sp)	#Push parameter onto the stack
	jal	fun_add		#Call the function.
	lw	$s7, -4($sp)	#Return the value of the function call
	add	$s8, $s6, $s7	#Evaluate binary operation
	move	$a0, $s8	#Load value to be printed into $a0
	li	$v0, 1		#Print boolean/integer
	syscall
	la	$a0, newline
	li	$v0, 4		#$Print newline
	syscall
	addi	 $sp, $sp, 36	#Reset the stack pointer when leaving the compound statement
	# Restore registers
	lw $s0, -36($sp)
	lw $s1, -32($sp)
	lw $s2, -28($sp)
	lw $s3, -24($sp)
	lw $s4, -20($sp)
	lw $s5, -16($sp)
	lw $s6, -12($sp)
	lw $s7, -8($sp)
	lw $s8, -4($sp)
	# exit
	li	$v0, 10
	syscall

	# All memory structures are placed after the
	# .data assembler directive
	.data

newline:	.asciiz "\n"
label3:	.asciiz	"This program should print the integers from 1 to n."
