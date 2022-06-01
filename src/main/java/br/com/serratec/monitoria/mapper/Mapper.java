package br.com.serratec.monitoria.mapper;

public interface Mapper<DTO, MODEL> {
	
	public DTO toDto(MODEL model);
	
	public MODEL toModel(DTO dto);

}
